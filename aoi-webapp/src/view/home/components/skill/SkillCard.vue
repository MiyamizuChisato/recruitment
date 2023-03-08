<script setup>

import ImageIcon from '../../../components/ImageIcon.vue';
</script>
<template>
    <div class='skill-card' rounded='2' bg-container shadow-box border v-ripple>
        <p m='b-20px' text='12px' text-regular>技能</p>
        <div m='b-30px' font='600' text='36px' text-strong>开启创造力</div>
        <div w='100px' rounded='full' p='1' flex-center-x border>
            <image-icon m='x-1' image='/src/assets/images/icons8-nginx-100.png' size='32px' />
            <span font='600' m='x-1' text-regular>Nginx</span>
        </div>
    </div>
</template>

<style scoped>
.skill-card {
    display: flex;
    line-height: 1;
    position: relative;
    padding: 20px 40px;
    flex-direction: column;
}
</style>