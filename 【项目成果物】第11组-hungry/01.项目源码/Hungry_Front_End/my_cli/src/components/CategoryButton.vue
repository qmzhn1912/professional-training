<template>
<button :style="{ '--hover-color': categoryStyle.color }" class="inline">
    <div class="center">
        <div class="icon">
            <img :src="categoryStyle.img" :alt="categoryStyle.name">
        </div>
        <h5>{{ categoryStyle.name }}</h5>
    </div>
</button>
   
</template>

<script setup>
defineProps(['categoryStyle'])
</script>

<style scoped>
button
{
    color: var(--green-main);

    width: 170px;
    height: 130px;
    margin-top: 20px;
    margin-right: 20px;

    background-color: #fff;

    transition: all 0.2s;
}

button:hover
{
    color: #fff;

    background-color: var(--hover-color);
}

.icon
{
    overflow: hidden;

    height: 65px;
    margin-bottom: 5px;
}

img
{
    height: 100%;
    transform: translateX(200px);
    filter: drop-shadow(var(--green-main) -200px 0px 0px);
}

button:hover img
{
    filter: drop-shadow(#fff -200px 0px 0px);
}

h5
{
    font-weight: 600;
}
</style>