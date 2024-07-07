<template>
  <div id="container"></div>
</template>

<script setup>
import { onMounted, onUnmounted } from 'vue';

const businessAddress = window.localStorage.getItem('localAddress')
let map = null;
onMounted(() => {
  initMap();
});

onUnmounted(() => {
  if (map) {
    map.destroy();
  }
});

function initMap() {
  map = new BMapGL.Map('container');
  map.centerAndZoom(businessAddress, 15);
  var myGeo = new BMapGL.Geocoder();
  myGeo.getPoint(businessAddress, function(point) {
    if (point) {
      map.centerAndZoom(point, 15);
      var marker = new BMapGL.Marker(point);
      map.addOverlay(marker);
      marker.addEventListener('click', function(e) {
        showLocationDetails(e.target.getPosition());
      });
    } else {
      alert("地址有误");
    }
  });

  map.addEventListener('rightclick', function(e) {
    showLocationDetails(e.point);
  });
}

function showLocationDetails(point) {
  var infoWindow = new BMapGL.InfoWindow('', {
    width: 250,
    height: 100,
    title: '位置信息'
  });

  var gc = new BMapGL.Geocoder();
  gc.getLocation(point, function(rs) {
    var addComp = rs.addressComponents;
    var address = addComp.province + ', ' + addComp.city + ', ' + addComp.district + ', ' + addComp.street + ', ' + addComp.streetNumber;

    // 将 BD-09 坐标转换为 WGS-84 坐标
    var wgsPoint = convertBD09ToWGS84(point.lng, point.lat);

    infoWindow.setContent(address + '<br>经度: ' + wgsPoint.lng + ', 纬度: ' + wgsPoint.lat);
    map.openInfoWindow(infoWindow, point);
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
.anchorBL{
  display:none;
}
</style>