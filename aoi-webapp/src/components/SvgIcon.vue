<script setup>
import { ref } from 'vue';

const props = defineProps({
    icon: String
});
const src = ref(props.icon);
const onerror = () => {
    src.value = 'https://api.iconify.design/material-symbols:broken-image.svg';
};
</script>
<template>
    <img class='svg-icon' :src='src' alt='icon' @error='onerror'>
</template>


<style scoped>
.svg-icon {
    width: 16px;
    height: 16px;
    display: block;
    filter: drop-shadow(1000px 0 0 var(--c-regular));
    transform: translateX(-1000px);
}
</style>