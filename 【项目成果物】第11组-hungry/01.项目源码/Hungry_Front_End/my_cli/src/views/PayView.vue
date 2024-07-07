<template>
<h2 class="margin-1">确认你的订单并支付。</h2>
<div class="left order">
    <div class="bottom"></div>
    <img src="@/assets/images/订单信息背景.jpg" alt="订单信息" class="mid">
    <div class="top">
        <h2 class="white"><span style="color: var(--green-pale)">订</span>单信息</h2>
        <div class="detail">
            <header><h4>{{ orderInfo.business }}</h4></header>
            <order v-for="i in foodDetails" :food="i"></order>
        </div>
        <footer class="margin-2">
            <div class="left"><h4>总价：</h4></div>
            <h2 class="right">￥{{ orderInfo.totalPrize }}</h2>
        </footer>
    </div>  
</div>
<div class="right">
    <div class="white address">
        <div class="bottom"></div>
        <img src="@/assets/images/收货地址背景.jpg" alt="收货地址" class="mid">
        <div class="top">
            <h2><span style="color: var(--yellow-green-pale)">收</span>货地址</h2>
            <h4>{{ userInfo.address }}</h4>
          <h6>{{ userInfo.userName }}    {{ userInfo.tel }}</h6>
        </div>
    </div>
    <div class="margin-2 white method">
        <div class="top"></div>
        <img src="@/assets/images/支付方式背景.jpg" alt="收货地址" class="mid">
        <div class="top">
            <h2 class="white"><span style="color: var(--yellow-pale)">付</span>款方式</h2>
            <button @click="pay"><h5 class="center">支付 ￥{{ orderInfo.totalPrize }}</h5></button>
        </div>       
    </div>
</div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import router from '@/router'
import order from '@/components/OrderBar.vue'
import {useRoute} from "vue-router";
import axios from "axios";

const business = JSON.parse(sessionStorage.getItem("business"))
const totalPrice = useRoute().query.totalPrice
const orderId = useRoute().query.orderId
console.log(totalPrice)
const orderInfo = ref({id: business.businessId, business: business.businessName, totalPrize: totalPrice})
const foodDetails = ref([])
const userInfo = ref(JSON.parse(window.localStorage.getItem('userInfo')))
console.log(userInfo)
onMounted(() =>
{
  axios.get(`http://localhost:8001/deliveryaddress/get`,{params:{userId:userInfo.value.userId}})
      .then(response =>
      {
       console.log(response.data.data)
        userInfo.value["address"] = response.data.data.address
        userInfo.value["tel"] = response.data.data.contactTel
      })
      .catch(error =>
      {
        alert(error)
      })
  axios.get(`http://localhost:8001/orderDetail/get`,{params:{orderId:orderId}})
      .then(response =>
      {
        // console.log(response.data.data)
        foodDetails.value=response.data.data
        console.log((foodDetails.value))
      })
      .catch(error =>
      {
        alert(error)
      })
})
function pay(){
  router.push({
    path: '/succeed'
  })
}
</script>

<style scoped>
.order, .detail, .address, .method
{
    border-radius: 10px;
    border-width: 0; 
    
    box-shadow: 0 2px 6px rgba(0,0,0,0.4);
}

.order, .address, .method
{
    position: relative;

}

.order
{
    width: calc(40% - 20px);
    height: calc(100% - 70px - 20px);
}

.order > .bottom
{
    background-color: var(--green-main);
    opacity: 10%;
}

.order > .mid
{
    left: 0;
    width: 100%;

    top: 40%;
    height: 60%;

    opacity: 20%;
}

.order > .top
{
    padding: 20px;

    background: linear-gradient(var(--green-main) 40%, rgba(0,0,0,0));
}

.detail
{
    overflow: auto;

    height: calc(100% - 130px);
    padding: 10px;

    background-color: #fff;
    box-shadow: 0 2px 6px rgba(0,0,0,0.4);
}

.detail > header
{
    margin-bottom: 10px;
}

footer
{
    color: var(--green-dim);
}

div.right
{
    width: 60%;
}

.address
{
    height: 200px;
}

.address > .bottom
{
    background-color: var(--yellow-green-main);
    opacity: 30%;
}

.address > .mid
{
    left: 40%;
    width: 60%;

    top: 0;
    height: 100%;

    opacity: 40%;
}

.address > .top
{
    padding: 20px;
    background: linear-gradient(0.25turn, var(--yellow-green-main) 40%, rgba(0,0,0,0));
}

.method
{
    height: calc(100vh - var(--bar-height) - 90px - 220px);
}

.method > .bottom
{
    background-color: var(--yellow-green-main);
    opacity: 30%;
}

.method > .mid
{
    left: 0%;
    width: 100%;

    top: 0;
    height: 100%;

    opacity: 40%;
}

.method > .top
{
    padding: 20px;
    background: linear-gradient(0.25turn, var(--yellow-main) 20%, rgba(0,0,0,0));
}
</style>