import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useCartStore = defineStore('cart', () => {
    const userId = ref('')
    const businessId = ref('')

    const cartFoods = ref([])
    const allFoods = ref([])

    const getCartFoodsCount = computed(() => {
        return cartFoods.value.length
    })

    const getAllFoodsCount = computed(() => {
        return allFoods.value.length
    })

    function updateStore(id, quantity){
        for(var i = 0; i < getCartFoodsCount.value; i++){
            if(cartFoods.value[i].foodId === id){
                cartFoods.value[i].quantity = quantity
                for(var j = 0; j < getAllFoodsCount.value; j++){
                    if(allFoods.value[j].foodId === cartFoods.value[i].foodId){
                        allFoods.value[j].quantity = quantity
                    }
                }
                if(cartFoods.value[i].quantity === 0){
                    cartFoods.value.splice(i, 1)
                }
                updateCart()
                return
            }
        }
        for(i = 0; i < getAllFoodsCount.value; i++){
            if(allFoods.value[i].foodId === id){
                cartFoods.value.push({
                    userId: userId.value,
                    businessId: businessId.value,
                    foodId: allFoods.value[i].foodId,
                    name: allFoods.value[i].foodName,
                    price: allFoods.value[i].foodPrice,
                    quantity: quantity
                })
                allFoods.value[i].quantity = quantity
                updateCart()
                return
            }
        }
    }

    function updateCart() {
        for (var j = 0; j < cartFoods.value.length; j++) {
            axios.post(`http://localhost:8001/cart/update`,
                {
                    foodId: cartFoods.value[j].foodId,
                    quantity: cartFoods.value[j].quantity,
                    businessId: businessId.value,
                    userId: userId.value
                })
                .then(response => {
                    console.log(response)
                })
                .catch(error => {
                    alert(error)
                })
        }
    }
    function emptyCart(){
        cartFoods.value = []
        for(var i = 0; i < getAllFoodsCount.value; i++){
            allFoods.value[i].quantity = 0
        }
        //后端清空
    }

    function setUserId(e) {
        userId.value = e
    }

    function setBusinessId(e) {
        businessId.value = e
    }
    function setCartFoods(foods) {
        cartFoods.value = foods
    }
    function setAllFoods(foods) {
        allFoods.value = foods
    }

    return {cartFoods, allFoods,
        getCartFoodsCount, getAllFoodsCount,
        updateStore, emptyCart,
        setUserId, setBusinessId, setCartFoods, setAllFoods}
},{
    persist:{
        enabled: true
    }
})