<template>
<img src="@/assets/images/欢迎页背景.jpg" alt="背景图" class="bottom">
<div class="mid"></div>
<div class="out top" ref="outer">
    <div class="in">
        <slot></slot>
    </div>
</div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router';

const outer = ref() //路由更新时重置滚动位置
const watcher = watch(useRoute(), () => {
    if(outer.value){
        outer.value.scrollTo(0,0)
    }
})
</script>

<style scoped>
.bottom, .mid, .top
{
    border-radius: 0;
}

.bottom
{
    top: var(--bar-height);
    height: calc(100% - var(--bar-height));

    object-fit: cover;
    opacity: 20%;
}

.mid
{
    left: calc((100vw - 133vh - 100px) / 2);
    width: calc(133vh + 100px);

    top: var(--bar-height);
    height: calc(100% - var(--bar-height));
    background-color: rgba(255,255,255,0.6);
    backdrop-filter: blur(10px);
}

.top
{
    top: var(--bar-height);
    height: calc(100% - var(--bar-height));
}

.in
{
    overflow: auto;

    height: calc(100vh - var(--bar-height));
    min-height: calc(var(--min-view-height) - var(--bar-height));
}
</style>
