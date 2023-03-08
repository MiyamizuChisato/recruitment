import { useAppStore } from '../store/appStore.js';
import { computed, watch } from 'vue';

export const styleToggle = () => {
    const appStore = useAppStore();
    const dark = computed(() => appStore.theme === 'dark');
    const codeLight = document.getElementById('codeLight');
    const codeDark = document.getElementById('codeDark');
    return watch(dark, (value) => {
        if (value) {
            codeLight.disabled = true;
            codeDark.disabled = false;
        } else {
            codeLight.disabled = false;
            codeDark.disabled = true;
        }
    }, { immediate: true });
};