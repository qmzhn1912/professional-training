<template>
<div class="out">
    <div class="center">
        <h2 class="margin-1">支付成功！我们将尽快配送你的餐点。</h2>
        <div class="options">
            <button class="home" @click="toHome"><div class="center">返回主菜单</div></button>
        </div>
    </div>   
</div>
</template>

<script setup>
import router from '@/router'

const toHome = () => {
  router.push({
    path: '/home',
  })
}
</script>

<style scoped>
.options > button
{
    display: inline-block;

    width: 100%;
    margin: 0 10px;
}
</style>