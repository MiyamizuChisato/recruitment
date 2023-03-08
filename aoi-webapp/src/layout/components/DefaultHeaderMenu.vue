<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { go } from '../../utils/router.js';

const router = useRouter();
const toggle = computed(() => router.currentRoute.value);
</script>
<template>
    <v-btn-toggle selected-class='selected' v-model='toggle.name' rounded='0' h='!54px' mandatory>
        <v-btn @click='go("/home")' class='btn-menu' value='home' bg='!transparent' m='!x-2' p='!x-7' flat>
            <span text-strong>主页</span>
        </v-btn>
        <v-btn @click='go("/position")' class='btn-menu' value='position' bg='!transparent' m='!x-2' p='!x-7' flat>
            <span text-strong>职位</span>
        </v-btn>
        <v-btn @click='go("/relation")' class='btn-menu' value='relation' bg='!transparent' m='!x-2' p='!x-7' flat>
            <span text-strong>消息</span>
        </v-btn>
    </v-btn-toggle>
</template>
<style scoped>
.btn-menu {
    transition: none;
}

.btn-menu:hover {
    background-color: var(--c-hover) !important;
}

:deep(button.v-btn--active) {
    color: transparent !important;
    background-color: var(--c-selected) !important;
}
</style>