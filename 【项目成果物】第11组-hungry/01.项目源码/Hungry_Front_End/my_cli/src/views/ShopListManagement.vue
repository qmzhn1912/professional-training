<!-- src/views/ShopListManagement.vue -->
<template>
  <div class="shop-list-management">
    <h1>管理商家列表</h1>
    <ul>
      <li v-for="(shop, index) in shops" :key="index" class="shop-item">
        <div class="shop-info">
          <img :src="shop.image" alt="shop.name" width="100" />
          <span>{{ shop.name }}</span>
          <span>{{ shop.address }}</span>
        </div>
        <div class="shop-status">
          <label>
            状态:
            <select v-model="shop.status" @change="updateStatus(index)">
              <option value="营业中">营业中</option>
              <option value="暂停营业">暂停营业</option>
              <option value="已关停">已关停</option>
            </select>
          </label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const shops = ref([
  { name: '商家A', image: 'http://example.com/shopA.jpg', address: '地址A', status: '营业中' },
  { name: '商家B', image: 'http://example.com/shopB.jpg', address: '地址B', status: '暂停营业' },
  { name: '商家C', image: 'http://example.com/shopC.jpg', address: '地址C', status: '已关停' },
]);

function updateStatus(index) {
  // 更新商家状态
  alert(`商家 ${shops.value[index].name} 状态已更新为 ${shops.value[index].status}`);
}
</script>

<style scoped>
.shop-list-management {
  padding: 20px;
}

.shop-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  padding: 10px;
}

.shop-info {
  display: flex;
  align-items: center;
}

.shop-info img {
  margin-right: 10px;
}

.shop-status {
  display: flex;
  align-items: center;
}

select {
  margin-left: 5px;
}
</style>
