<template>
<hungryHeader :userInfo="userInfo"></hungryHeader>
<hungryMain>
    <router-view @login="login"/>
</hungryMain>
</template>

<script setup>
import { ref } from 'vue'
import hungryHeader from './components/Header.vue'
import hungryMain from './components/MainArea.vue'

const isLogin = ref(false)
</script>

<!--杂项-->
<style>
*
{
    box-sizing: border-box;
}

@font-face
{
    font-family: Oswald;
    src: url("@/assets/Oswald-Regular.ttf") format("opentype");
}

body
{
    color: var(--green-dim);

    margin: 0;
}
</style>

<!--模块-->
<style>
.out
{
    display: flex;

    width: 100%;
}

.in
{
    width: 100%;
    padding: 0 calc((100vw - 133vh) / 2);
    margin: auto;
}

.left
{
    display: inline-block;

    font-size: 0px;
}

.right
{
    float: right;
}

.bottom, .mid, .top
{
    position: absolute;

    border-radius: 10px;
}

.bottom, .top
{
    left: 0;
    width: 100%;

    top: 0;
    height: 100%;
}

.bottom
{
    z-index: 0;
}

.mid
{
    object-fit: cover;
    z-index: 1;
}

.top
{
    z-index: 2;
}
</style>

<!--文字样式-->
<style>
h1
{
    font-size: 50px;
    font-weight: 600;
    font-family: Oswald;
}

h2
{
    font-size: 32px;
    font-weight: 600;
    line-height: 50px;

    margin: 0 0 10px 0;
}

/*Logo*/
h3
{
    font-size: 28px;
    font-family: Oswald;
    font-weight: 600;

    margin: 0;
}

h4
{
    font-size: 24px;
    font-weight: 600;

    margin: 0;
}

h5
{
    font-size: 20px;
    font-weight: 600;

    margin: 0;
}

h6
{
    font-size: 20px;
    font-weight: 400;

    margin: 0;
}
</style>

<!--按钮和输入框-->
<style>
button
{
    display: flex;
    overflow: hidden;

    color: var(--green-dim);
    font-size: 20px;
    font-weight: 600;
   
    height: 48px;
    border-radius: 10px;
    border-width: 0;
    box-shadow: 0 2px 6px rgba(0,0,0,0.4);

    background-color: #fff;

    transition: all 0.2s;
}

button:hover
{
    color: #fff;

    background-color: var(--green-dim);
    
    transition: all 0.2s;
}

input
{
    display: block;

    color: var(--green-dim);
    font-size: 20px;

    width: 100%;
    height: 48px;
    border-radius: 10px;
    border-width: 0;
    padding-left: 10px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.4);

    background-color: #fff;
}

input::placeholder
{
    color: var(--green-main);
    font-size: 20px;
}

input:focus-visible
{
    outline-color: var(--green-dim);
}

input[type="checkbox"]
{
    width: 30px;
    margin-right: 10px;

    box-shadow: none;
}

select
{
    display: inline-block;

    color: var(--green-main);
    color: var(--green-dim);
    font-size: 20px;

    height: 48px;
    width: 100px;
    margin-left: 10px;
    border-radius: 10px;
     border: 2px solid var(--green-main);
}

select:focus-visible
{
    outline-color: var(--green-dim);
}

small
{
    display: inline-block;

    color: var(--red-main);
    font-size: 15px;

    margin-top: 5px;
    margin-left: 10px;
}
</style>

<!--变量-->
<style>
body
{
    --red-main: #BD3124;

    --orange-pale: #C0AB9A;
    --orange-main: #E99D42;

    --yellow-pale: #FFED85;
    --yellow-main: #F2B800;
    --yellow-dim: #A16222;
    
    --yellow-green-pale: #EAFFA3;
    --yellow-green-main: #97B337;

    --green-pale: #CAE898;
    --green-main: #81B337;
    --green-dim: #335402; 

    --turquoise-main: #62BAA3;

    --pink-main: #DE868F;

    --max-view-width: 3840px;
    --max-view-height: 2160px;
    --min-view-width: 1200px;
    --min-view-height: 675px;

    --bar-height: 50px;
}
</style>

<!--格式控制-->
<style>
.margin-1
{
    margin-top: 10px;
}

.margin-2
{
    margin-top: 20px;
}

.white
{
    color: #fff;
}

.center
{
    margin: auto;
}

.vertical-center
{
    margin: auto 0;
}

.inline
{
    display: inline-block;
}
</style>