<script setup>
import { onMounted, ref } from 'vue';

const enable = ref(false);
onMounted(() => {
    let top = 0;
    window.addEventListener('scroll', () => {
        const newTop = document.documentElement.scrollTop;
        enable.value = newTop > window.innerHeight / 2 && newTop < top;
        top = newTop;
    });
});
const returnTopTrigger = () => {
    document.documentElement.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
};
</script>
<template>
    <transition enter-active-class='animate__animated animate__fadeInUp'
                leave-active-class='animate__animated animate__zoomOut'>
        <v-btn @click='returnTopTrigger' v-if='enable' class='trigger' position='fixed' size='40'
               right='5' bottom='5' bg-container pointer flat icon>
            <i text='7' i-material-symbols-rocket text-danger />
        </v-btn>
    </transition>
</template>
<style scoped>
.trigger {
    z-index: 99;
    border: 1px solid var(--c-danger);
}
</style>
