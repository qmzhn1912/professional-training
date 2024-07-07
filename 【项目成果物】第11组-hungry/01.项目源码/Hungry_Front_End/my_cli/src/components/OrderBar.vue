<template>
<div class="bar">
    <div class="left"><h6>{{ food.foodName }} x{{ food.quantity }}</h6></div>
    <h5 class="right">￥{{ (food.foodPrice * food.quantity).toFixed(2) }}</h5>
</div>
</template>

<script setup>
const prop = defineProps(['food'])
</script>

<style scoped>
.bar
{
    height: 40px;
}
</style>