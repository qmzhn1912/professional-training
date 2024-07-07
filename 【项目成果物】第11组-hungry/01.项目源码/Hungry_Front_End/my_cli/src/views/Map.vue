<template>
  <div>
    <input v-model="address" type="text" placeholder="输入地址" />
    <button @click="searchAddress()">搜索</button>
    <button @click="ConfirmAddress()">确认地址</button>
    <div id="container" style="height: 100vh;"></div>
  </div>
</template>

<script setup>
import { ref,onMounted, onUnmounted } from 'vue';
import router from "@/router";
import axios from 'axios';

let map = null;

const address=ref("武汉市洪山区华中科技大学工程实践创新基地")

onMounted(() => {
  initMap();
  initMenu();
});

onUnmounted(() => {
  if (map) {
    map.destroy();
  }
});

function initMap() {
  map = new BMapGL.Map('container');
  map.centerAndZoom('武汉市', 15);

  var myGeo = new BMapGL.Geocoder();
  myGeo.getPoint('武汉市', function (point) {
    if (point) {
      map.centerAndZoom(point, 15);
      var marker = new BMapGL.Marker(point);
      map.addOverlay(marker);
      marker.addEventListener('click', function (e) {
        showLocationDetails(e.target.getPosition());
      });
    } else {
      alert("地址有误");
    }
  });

  map.addEventListener('click', function(e) {
    // 创建一个 BMapGL.Point 实例
    var point = new BMapGL.Point();

    // 设置经度和纬度属性
    point.lng = e.latlng.lng;
    point.lat = e.latlng.lat;
    showLocationDetails(point);
  });

}



function initMenu(){
  var menu = new BMapGL.ContextMenu();
  var txtMenuItem = [
    {
      text: '放大',
      callback: function() {
        map.zoomIn();
      }
    },
    {
      text: '缩小',
      callback: function() {
        map.zoomOut();
      }
    },
    {
      text: '确认地址',
      callback: function() {
        ConfirmAddress();
      }
    }
  ];
  for (var i = 0; i < txtMenuItem.length; i++) {
    menu.addItem(new BMapGL.MenuItem(txtMenuItem[i].text, txtMenuItem[i].callback));
  }
  map.addContextMenu(menu);
}

function searchAddress() {
  console.log(address.value)
  var myGeo = new BMapGL.Geocoder();
  myGeo.getPoint(address.value, function(point) {
    if (point) {
      map.centerAndZoom(point, 18);
      var marker = new BMapGL.Marker(point);
      map.addOverlay(marker);
      marker.addEventListener('click', function(e) {
        showLocationDetails(e.target.getPosition());
      });
    } else {
      alert("您输入的地址有误");
    }
  });
}


function showLocationDetails(point) {
  var infoWindow = new BMapGL.InfoWindow('', {
    width: 250,
    height: 100,
    title: '位置信息'
  });

  var gc = new BMapGL.Geocoder();
  gc.getLocation(point, function (rs) {
    var addComp = rs.addressComponents;
    var address1 = addComp.province + ', ' + addComp.city + ', ' + addComp.district + ', ' + addComp.street + ', ' + addComp.streetNumber ;
    address.value = address1;
    // 将 BD-09 坐标转换为 WGS-84 坐标
    var wgsPoint = convertBD09ToWGS84(point.lng, point.lat);

    infoWindow.setContent(address1 + '<br>经度: ' + wgsPoint.lng + ', 纬度: ' + wgsPoint.lat);
    map.openInfoWindow(infoWindow, point);
  });
}






function ConfirmAddress() {
  var businessId = window.localStorage.getItem('businessId');
  axios.get(`http://localhost:8001/business/updateAddress/${businessId}`, {params:{
    businessAddress: address.value    
  }}).then(Response => {
    console.log(Response.data.data.businessAddress);
  }).
    catch(error => {
    console.log(error);
  });
  router.push({
    path: '/shop-management',
    query: {
      address: address.value
    }
  });

}



function convertBD09ToWGS84(bdLng, bdLat) {
  var X_PI = Math.PI * 3000.0 / 180.0;
  var x = bdLng - 0.0065;
  var y = bdLat - 0.006;
  var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
  var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
  var ggLng = z * Math.cos(theta);
  var ggLat = z * Math.sin(theta);

  return {
    lng: ggLng,
    lat: ggLat
  };
}
</script>

<style>
#container {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>