<script setup>
import { useAppStore } from '../../../store/appStore.js';
import { computed } from 'vue';

const appStore = useAppStore();
const theme = computed(() => appStore.theme);
</script>
<template>
    <div m='x-3'>
        <v-btn @click='appStore.toggleTheme' size='small' color='transparent' text-strong flat icon>
            <i data-aos='animate__zoomIn' data-aos-once='true' i-material-symbols-light-mode
               v-if='theme==="dark"' />
            <i data-aos='animate__zoomIn' data-aos-once='true' i-material-symbols-dark-mode v-else />
        </v-btn>
    </div>
</template>
