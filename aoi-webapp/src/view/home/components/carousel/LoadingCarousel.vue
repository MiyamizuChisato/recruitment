<script setup>
</script>
<template>
    <div rounded='2' class='carousel' flex-center>
        <v-progress-circular :size='50' text-primary indeterminate />
    </div>
</template>
<style scoped>
.carousel {
    height: calc(100vh - 54px - 68px);
    color: var(--c-disable) !important;
    padding-bottom: 40px;
    min-height: 400px;
}
</style>