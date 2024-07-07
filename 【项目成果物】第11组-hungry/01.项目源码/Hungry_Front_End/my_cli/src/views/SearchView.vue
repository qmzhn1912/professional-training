<template>
<h2 class="margin-1">搜索结果：{{ searchInfo }}</h2>
<search @update="update" class="search-bar"></search>
<business v-for="result in results" :result="result"></business>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import search from '@/components/SearchBar.vue'
import business from '@/components/BusinessBar.vue'
import axios from "axios";

const temp = ref(useRoute())
const searchInfo = ref(useRoute().query.text)
const watcher = watch(temp.value, () => {
  searchInfo.value = temp.value.query.text
  axios.get('http://localhost:8001/business/search', {params:{key: searchInfo.value}

  }).then(response => {results.value = response.data.data})
      .catch(error => {alert(error)})
})
const results = ref([])
onMounted(() =>{
  axios.get('http://localhost:8001/business/search', {params:{key: searchInfo.value}

  }).then(response => {results.value = response.data.data})
      .catch(error => {alert(error)})
})
</script>

<style scoped>
.search-bar
{
    margin-bottom: 20px;
}
</style>