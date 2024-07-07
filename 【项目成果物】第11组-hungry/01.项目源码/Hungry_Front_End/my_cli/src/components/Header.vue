<template>
<div class="out white">
    <div class="in">
        <div class="left">
            <h3><span class="title">H</span>ungry?</h3>
        </div>
        <div class="right">
            <h6 v-if="login">欢迎，{{ userInfo.userName }}</h6>
        </div>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue'

const userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
//判断是否登录，登录则显示欢迎信息
const login = ref(window.localStorage.getItem('isLogin'))
</script>

<style scoped>
.out
{
    height: var(--bar-height);

    background: linear-gradient(0.25turn, var(--green-main), var(--green-dim));
}

h3, h6
{
    line-height: var(--bar-height);
}

.title
{
    color: var(--green-pale);
}
</style>