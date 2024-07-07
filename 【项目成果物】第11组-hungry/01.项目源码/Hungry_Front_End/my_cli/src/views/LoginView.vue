<template>
<div class="out">
    <div class="center">
        <h2 class="margin-1">登录你的 Hungry? 账户。</h2>
        <div class="identity">
            <choice v-for="identity in identities" :option="identity" @click="updateIdentity(identity.id)"></choice>
        </div>
        <input type="text" :placeholder=identities[option].name class="margin-2" v-model="loginInfo.userName">
        <small><span v-if="nameEmpty">请输入{{ identities[option].name }}</span></small>
        <input type="password" placeholder="密码" class="margin-1" v-model="loginInfo.password">
        <small>
            <span v-if="passwordEmpty">请输入密码</span>
            <span v-if="notFound">用户名或密码错误</span>
        </small>
        <!-- <div class="local"><input type="checkbox" v-model="local"><h5>保持登录</h5></div> -->
        <button @click="login"><div class="center">确定</div></button>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import router from '@/router'
import choice from '@/components/OptionButton.vue'

const emit = defineEmits(['userInfo'])

const loginInfo = ref({
    userName: "",
    password: ""
})

const local = ref(false)

const isOK = ref(false)
const userInfo = ref({})
const token = ref('')

const nameEmpty = ref(false)
const passwordEmpty = ref(false)
const notFound = ref(false)
const businessId = ref('')
const option = ref(0)
const identities = ref([
    {id: 0, buttonName: "用户", name: "用户名", color: "var(--green-main)", isChecked: true},
    {id: 1, buttonName: "商家", name: "店铺名", color: "var(--yellow-green-main)", isChecked: false},
    {id: 2, buttonName: "管理员", name: "管理员编号", color: "var(--yellow-main)", isChecked: false}
])

function updateIdentity(id){
    for(var i = 0; i < 3; i++){
        identities.value[i].isChecked = false
    }
    identities.value[id].isChecked = true
    option.value = id
}

function login(){
    if(loginInfo.value.userName === '') {nameEmpty.value = true}
    else{nameEmpty.value = false}
    
    if(loginInfo.value.password === '') {passwordEmpty.value = true}
    else{passwordEmpty.value = false}

    if(nameEmpty.value === false && passwordEmpty.value === false) {
        if (option.value === 0) {

            //用户登录
            axios({
                method: "post",
                baseURL: "http://localhost:8001",
                url: '/user/login',
                data: loginInfo.value,
            })
            .then(response => {
              isOK.value = response.data.result
              userInfo.value = response.data.data
              token.value = response.data.msg

              if(isOK.value === true) {
                notFound.value = false
                window.localStorage.setItem('login', true)
                window.localStorage.setItem('userInfo', JSON.stringify(userInfo.value))
                router.push('/home')
              }
              else {notFound.value = true}
            })
            .catch(error => {alert(error)})
        }
        else if(option.value === 1) {
        //商家登录
            axios({
                    method: "post",
                    baseURL: "http://localhost:8001",
                    url: '/user/login',
                    data: loginInfo.value,
                })
                .then(response => {
                console.log(isOK.value)
                console.log(response.data.data.userId)
                isOK.value = response.data.result
                if(isOK.value === true) {
                    window.localStorage.setItem('userId', response.data.data.userId)
                    router.push('/shop-management')
                }
                })
                .catch(error => {alert(error)})
        }
        else if(option.value === 2) {
        //管理员登录
        }
        

    }
}
</script>

<style scoped>
h2
{
    font-family: Oswald;
}

input
{
    margin-bottom: 5px;
}

input[type="text"], input[type="password"]
{
    width: 300px;
}

.local
{
    display: flex;

    height: 48px;
    margin-bottom: 10px;
}

.local > *
{
    margin: auto 0;
}

button
{
    width: 100px;
}
</style>