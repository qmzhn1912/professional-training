<template>
<div class="out">
    <div class="center">
        <h2 class="margin-1">注册你的 Hungry? 账户。</h2>
        <div class="identity">
            <choice v-for="identity in identities" :option="identity" @click="updateIdentity(identity.id)"></choice>
        </div>
        <input type="text" :placeholder=identities[option].name class="margin-2" v-model="name">
        <small><span v-if="nameError">请输入{{ identities[option].name }}</span></small>
        <input type="password" placeholder="密码" class="margin-1" v-model="password">
        <small><span v-if="passwordError">请输入密码</span></small>
        <!-- <div class="local"><input type="checkbox" v-model="local"><h5>保持登录</h5></div> -->
        <button @click="register"><div class="center">确定</div></button>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import router from '@/router'
import choice from '@/components/OptionButton.vue'

const name = ref('')
const password = ref('')
const local = ref(false)

const isOK = ref(false)

const error = ref(false)
const nameError = ref(false)
const passwordError = ref(false)

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

function register(){
    if(name.value === ''){
        error.value = true
        nameError.value = true
    }
    else{
        nameError.value = false
    }

    if(password.value === ''){
        error.value = true
        passwordError.value = true
    }
    else{
        passwordError.value = false
    }

    if(error.value === false){
        if(option.value === 0){
            //用户注册
            axios.post('http://localhost:8001/user/reg', {userName: name.value, password: password.value})
                .then(response => {
                    isOK.value = response.data.result

                    if(isOK.value === true){
                        router.push('/home')
                    }
                    else(alert('注册失败！'))
                })
                .catch(error => {alert(error)})
        }
        else if(option.value === 1){
            //商家注册
            axios.post('http://localhost:8001/business/register', {businessName: name.value}, {params:{password: password.value}}) //商家列表新建一个空的商家
                .then(response => {
                    // isOK.value = response.data.result
                    console.log(response.data.data)
                    // window.localStorage.setItem('businessId', response.data.data)
                    router.push('/shop-management',
                        {
                            query: {
                                userId: response.data.data
                            }
                        }
                    )
                    })
                .catch(error => {alert(error)})
        }
        else if(option.value === 2){
            //管理员注册
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