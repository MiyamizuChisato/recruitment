<script setup>
import AsyncCarousel from './AsyncCarousel.vue';
import LoadingCarousel from './LoadingCarousel.vue';
</script>
<template>
    <suspense>
        <template #default>
            <async-carousel />
        </template>
        <template #fallback>
            <loading-carousel />
        </template>
    </suspense>
</template>
