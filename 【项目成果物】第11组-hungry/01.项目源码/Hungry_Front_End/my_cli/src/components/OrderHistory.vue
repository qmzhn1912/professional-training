<!-- src/components/OrderHistory.vue -->
<template>
  <div class="order-history">
    <h1>历史订单</h1>
    <ul>
      <li v-for="(order, index) in sortedOrders" :key="order.id" class="order-item">
        <div class="order-info">
          <p>订单号: {{ order.id }}</p>
          <p>日期: {{ order.date }}</p>
          <p>内容: {{ order.content }}</p>
        </div>
        <button @click="deleteOrder(index)">删除订单</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

// 示例订单数据
const orders = ref([
  { id: 1, date: '2024-06-25', content: '食品A, 食品B' },
  { id: 2, date: '2024-06-24', content: '食品C' },
  { id: 3, date: '2024-06-23', content: '食品D, 食品E, 食品F' },
]);

const sortedOrders = computed(() => {
  return [...orders.value].sort((a, b) => new Date(b.date) - new Date(a.date));
});

function deleteOrder(index) {
  orders.value.splice(index, 1);
}
</script>

<style scoped>
.order-history {
  padding: 20px;
}

.order-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}

.order-info p {
  margin: 0;
}

button {
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

button:hover {
  background-color: #d9363e;
}
</style>
