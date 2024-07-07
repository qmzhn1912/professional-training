<template>
<div class="left order">
    <div class="business margin-2">
        <div class="left">
            <img :src="business.businessImg" :alt="business.businessName">
        </div>
        <div class="left">
            <h2>{{ business.businessName }}</h2>
            <h6>￥{{ business.startPrice }}起送，配送费￥{{ business.deliveryPrice }}</h6>
          <h6>地址：<a href="http://localhost:8080/map-search">{{ business.businessAddress }}</a></h6>
            <h6>介绍：{{ business.businessExplain }}</h6>
        </div>
    </div>
    <food v-for="food in cartStore.allFoods" :key="food.foodId" :food="food" ref="foodList"></food>
</div>
<div class="right cart">
    <header>
        <div class="left"><h2 class="margin-1">购物车</h2></div>
        <div class="right">
            <button v-if="cartStore.getCartFoodsCount === 0" class="disabled"><div class="center">清空</div></button>
            <button v-else @click="emptyCart"><div class="center">清空</div></button>
        </div>
    </header>
    <div class="detail">
        <cart v-for="food in cartStore.cartFoods" :food="food"></cart>
    </div>
    <footer>
        <button v-if="cartStore.getCartFoodsCount === 0" class="center disabled"><div class="center">确认订单</div></button>
        <button v-else class="center" @click="toPay"><div class="center">确认订单</div></button>
    </footer>
</div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue'
import router from "@/router";
import axios from "axios";
import { useCartStore } from "@/store/CartStore.js";

import food from '@/components/FoodBar.vue'
import cart from '@/components/CartFoodBar.vue'

const foodList = ref([])

const cartStore = useCartStore()

const userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
const business = JSON.parse(sessionStorage.getItem("business"))
cartStore.setUserId(userInfo.userId)
cartStore.setBusinessId(business.businessId)

onBeforeMount(() =>
{
  axios.get(`http://localhost:8001/food/${business.businessId}`)
  .then(response =>
  {
    cartStore.setAllFoods(response.data.data)
    //console.log(foods.value)
  })
  .catch(error =>
  {
    alert(error)
  })

  axios.get(`http://localhost:8001/cart/getInfo`,
  {params:{businessId:business.businessId,userId:userInfo.userId}})
  .then(response =>
  {
    cartStore.setCartFoods(response.data.data)

    for(var i = 0; i < cartStore.getAllFoodsCount; i++)
    {
      cartStore.allFoods[i]['quantity'] = 0
      for(var j = 0; j < cartStore.getCartFoodsCount; j++){
        if(cartStore.allFoods[i].foodId === cartStore.cartFoods[j].foodId){
          console.log(cartStore.allFoods[i], cartStore.cartFoods[j])
          //金风玉露一相逢，便胜却人间无数
          cartStore.allFoods[i].quantity = cartStore.cartFoods[j].quantity

          cartStore.cartFoods[j]['name'] = cartStore.allFoods[i].foodName
          cartStore.cartFoods[j]['price'] = cartStore.allFoods[i].foodPrice
        }
      }
    }
  })
  .catch(error =>
  {
    alert(error)
  })

  window.localStorage.setItem('localAddress', business.businessAddress)
})

function emptyCart(){
    cartStore.emptyCart()
}
const toPay = () => {
  console.log(business.businessId,userInfo.userId)
  axios.get(`http://localhost:8001/order/produce`,
      {params:{businessId:business.businessId,userId:userInfo.userId}})
      .then(response => {
        console.log(response)
        router.push({
          path: '/pay',
          query: {totalPrice:response.data.data.orderTotal,orderId:response.data.data.orderId}
        })
        cartStore.emptyCart()
      })
      .catch(error => {alert(error)})
}
</script>

<style scoped>
.order
{
    overflow-y: auto;

    width: calc(100% - 300px);
    height: calc(100% - 5px);
}

.business
{
    margin-bottom: 20px;
}

.business img
{
    width: 150px;
    height: 150px;
    margin-right: 10px;
    border-radius: 10px;
}

.cart
{
    width: 300px;
    height: 100%;
    border-left: 1px solid #ccc;
    border-right: 1px solid #ccc;
}

header
{
    border-bottom: 1px solid #ccc;
}

header > .left > h2
{
    padding-left: 20px;
    padding-bottom: 10px;
}

header > .right > button
{
    color: var(--red-main);

    width: 100px;
    margin-top: 15px;
    margin-right: 20px;
}

header > .right > button:hover
{
    color: #fff;

    background-color: var(--red-main);
}

.cart > .detail
{
    overflow: auto;

    height: calc(100% - 150px);
}

footer
{
    display: flex;

    padding-top: 10px;
}

footer > button
{
    color: var(--green-dim);

    width: 200px;
}

footer > button:hover
{
    color: #fff;

    background-color: var(--green-main);
}

header > .right > button.disabled,
footer > button.disabled
{
    color: #ccc;

    pointer-events: none;
}
</style>