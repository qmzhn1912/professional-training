<template>
<div class="out">
    <div class="center">
        <h1><span style="color: var(--green-main)">H</span>ungry? 尽享寻味之旅。</h1>
        <div class="options">
            <button class="login" @click="toLogin"><div class="center">登录</div></button>
            <button class="register" @click="toRegister"><div class="center">注册</div></button>
        </div>
    </div>   
</div>
</template>

<script setup>
import { onMounted } from 'vue'
import router from '@/router'

const toLogin = () => {
  router.push({
    path: '/login',
  })
}

const toRegister = () => {
    router.push({
        path: '/register',
    })
}

onMounted(() => {
    window.localStorage.setItem('isLocal', false)
})
</script>

<style>
.options > button
{
    display: inline-block;

    width: calc(50% - 20px);
    margin: 0 10px;
}

.login
{
    color: #fff;

    background-color: var(--green-main);
}

.register:hover
{
    background-color: var(--green-main);
}
</style>