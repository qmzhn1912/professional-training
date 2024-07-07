<template>
<div class="bar" @click="toOrder()">
    <div class="left">
        <img :src="result.businessImg" :alt="result.businessName">
    </div>
    <div class="left">
        <h6>{{ result.class }}</h6>
        <h4>{{ result.businessName }}</h4>
        <h6 class="margin-1">￥{{ result.startPrice }}起送，配送费￥{{ result.deliveryPrice }}</h6>
    </div>
</div>
</template>

<script setup>
import router from '@/router'

const prop = defineProps(['result'])

const toOrder = () => {
    sessionStorage.setItem("business",JSON.stringify(prop.result))
    router.push({
        path: '/order'
    })
}
</script>

<style scoped>
.bar
{
    display: inline-block;

    width: calc((100% - 40px) / 2);
    padding: 20px;
    margin-bottom: 20px;
    margin-right: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.4);

    background-color: #fff;
}

.left
{
    overflow: hidden;
}

img
{
    width: 100px;
    height: 100px;
    margin-right: 20px;

    border-radius: 10px;;
}
</style>