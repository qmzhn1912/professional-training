<template>
<div v-if="food.quantity > 0" class="bar">
    <div class="left"><h6>{{ food.name }} x{{ food.quantity }}</h6></div>
    <div class="right"><h5>￥{{ (food.price * food.quantity).toFixed(2) }}</h5></div>
</div>
</template>

<script setup>
const prop = defineProps(['food'])
</script>

<style scoped>
.bar
{
    height: 50px;
    padding: 10px 20px 5px 20px;
    border-bottom: 1px solid #ccc;
}
</style>