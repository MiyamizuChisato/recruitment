<script setup>
import DefaultHeader from './components/DefaultHeader.vue';
import DefaultBody from './components/DefaultBody.vue';
import DefaultFooter from './components/DefaultFooter.vue';
</script>

<template>
    <default-header />
    <default-body />
    <default-footer />
</template>
<style>
#app {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}
</style>