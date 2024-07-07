<template>
<button :style="{ '--hover-color': option.color }" :class="{checked: option.isChecked}">
{{ option.buttonName }}
</button>
</template>

<script setup>
const prop = defineProps(['option'])
</script>

<style scoped>
button
{
    display: inline-block;

    width: 100px;
    margin-right: 10px;
}

button:hover
{
    background-color: var(--hover-color);
}

.checked
{
    border: 4px solid var(--hover-color);
}
</style>