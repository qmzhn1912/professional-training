<template>
  <div class="shop-management">
    <!-- 店铺设置 -->
    <div class="shop-settings">
      <h2>店铺设置</h2>

      <label>店铺名称:</label>
      <input v-model="business.businessName" placeholder="店铺名称" />

      <label>
        店铺封面:
        <input @change="uploadBusinessPhoto($event)" type="file" class="kyc-passin">
        <img :src="business.businessImg" alt="">
      </label>

      <label>
        起送费:
        <input v-model="business.startPrice" type="number" />
      </label>

      <label>
        配送费:
        <input v-model="business.deliveryPrice" type="number" />
      </label>

      <label>
        店铺地址:
        <input v-model="business.businessAddress" placeholder="店铺地址" />
      </label>

      <button @click="saveSettings">保存设置</button>
      <a href="http://localhost:8080/map">设置地址详情</a>


    </div>

    <!-- 食品管理 -->
    <div class="food-management">
      <h2>食品管理</h2>
      <button @click="addFood">新增食品</button>
      <div v-if="isEditing" class="food-edit">
        <h2>{{ editIndex === -1 ? '新增食品' : '编辑食品' }}</h2>
        <input v-model="addedFood.foodName" placeholder="食品名称" />
        <input @change="uploadAddedPhoto($event)" type="file" class="kyc-passin">
        <img :src="addedFood.foodImg" alt="">
        <input v-model="addedFood.foodPrice" placeholder="食品价格" type="number" />
        <button @click="saveAddedFood">保存</button>
        <button @click="cancelEdit">取消</button>
      </div>
    </div>
    <ul>
      <li v-for="(food, index) in foods" :key="index" class="food-item">
        <input @change="uploadPhoto($event, index)" type="file" class="kyc-passin">
        <img :src="food.foodImg" alt="">
        <input v-model="food.foodName" placeholder="食品名称" />
        <input v-model="food.foodPrice" placeholder="食品价格" type="number" />
        <button @click="saveFood(index)">保存更改</button>
        <button @click="removeFood(index)">删除</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from "axios";
import { useRoute } from 'vue-router'
const business = ref({});
const businessId = ref();
const foods = ref([]);
const isEditing = ref(false);
const editIndex = ref(-1);
const addedFood = ref({});
const userId = ref(window.localStorage.getItem('userId'))
console.log(userId.value)
const address = ref(useRoute().query.address)

onMounted(() => {
  console.log(userId.value)
  axios.get(`http://localhost:8001/business/getBusinessId`, { params: { userId: userId.value } })
    .then(response => {
      businessId.value = response.data.data.businessId
      console.log(businessId.value)
      window.localStorage.setItem('businessId', businessId.value)
      getBusinessInfo(businessId.value)
    })
    .catch(error => {
       alert(error) 
      })
})


function getBusinessInfo(businessId) {

  
  axios.get(`http://localhost:8001/business/getInfo/${businessId}`)
   .then(response => {
      business.value = response.data.data
      // business.value.businessAddress = address.value
     console.log(business.value)
  })
  .catch(error => { alert(error) })


  axios.get(`http://localhost:8001/food/${businessId}`
  ).then(response => {
    foods.value = response.data.data
    console.log(foods.value)
  })
  .catch(error => { alert(error) })

}

function addFood() {
  addedFood.value = { foodName: '', foodImg: '', foodPrice: 0 };
  isEditing.value = true;
  editIndex.value = -1;
}


function removeFood(index) {
  console.log(foods.value[index]);
  axios.delete(`http://localhost:8001/food/del`,
    { params: { foodId: foods.value[index].foodId } })
    .then(response => {
      console.log(response.data)
      foods.value.splice(index,1)
    })
    .catch(error => { alert(error) })
}

function saveFood(index) {
  console.log(foods.value[index]);
  axios.put(`http://localhost:8001/food/update`, foods.value[index])
    .then(response => {
      console.log(response.data)
    })
    .catch(error => { alert(error) })
}
function saveAddedFood(index) {
  foods.value.push(addedFood.value)
  axios.post(`http://localhost:8001/food/add/${businessId.value}`, addedFood.value)
    .then(response => {
      console.log(response.data)
    })
    .catch(error => { alert(error) })
}

function cancelEdit() {
  isEditing.value = false;
}

function saveShopInfo() {
  alert('店铺信息已保存');
}

function saveSettings() {
  axios.put(`http://localhost:8001/business/update/${businessId.value}`, business.value)
    .then(response => {
      console.log(response.data)
    })
    .catch(error => { alert(error) })
  alert('店铺设置已保存');
}

function uploadAddedPhoto(e) {
  // 利用fileReader对象获取file
  var file = e.target.files[0];
  var filesize = file.size;
  if (filesize > 2101440) {
    // 图片大于2MB

  }
  var reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = (e) => {
    // 读取到的图片base64 数据编码 将此编码字符串传给后台即可
    addedFood.value.foodImg = e.target.result
  }
}
function uploadPhoto(e, index) {
  // 利用fileReader对象获取file
  var file = e.target.files[0];
  var filesize = file.size;
  if (filesize > 2101440) {
    // 图片大于2MB

  }
  var reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = (e) => {
    // 读取到的图片base64 数据编码 将此编码字符串传给后台即可
    foods.value[index].foodImg = e.target.result
    foods.value[index].foodPrice = 3.0
  }
}
function uploadBusinessPhoto(e) {
  // 利用fileReader对象获取file
  var file = e.target.files[0];
  var filesize = file.size;
  if (filesize > 2101440) {
    // 图片大于2MB

  }
  var reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = (e) => {
    // 读取到的图片base64 数据编码 将此编码字符串传给后台即可
    business.value.foodImg = e.target.result
  }
}
</script>

<style scoped>
.shop-management {
  padding: 20px;
}

.shop-info,
.food-management,
.shop-settings {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}

.shop-info-item,
.food-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.shop-info-item label,
.food-item span {
  margin-right: 10px;
}

button {
  margin-left: 10px;
}

input {
  margin: 5px 0;
  display: block;
}
</style>
