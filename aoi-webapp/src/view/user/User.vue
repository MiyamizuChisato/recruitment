<script setup>
import UserCard from './components/UserCard.vue';
import UserProfile from './components/UserProfile.vue';
import { ref } from 'vue';
import { useUserStore } from '../../store/userStore.js';
import UserPublish from './components/UserPublish.vue';
import UserManage from './components/UserManage.vue';
import UserSend from './components/UserSend.vue';

const tab = ref('info');
const editing = ref(false);
const userStore = useUserStore();
</script>
<template>
    <div v-if='userStore.signed' w='md:720px' p='lt-md:x-8px' margin-center>
        <user-card v-model='editing' />
        <div m='t-4 b-2' text-strong>
            <v-tabs v-model='tab' overflow='hidden' rounded='2' color='blue'
                    shadow-box hide-slider bg-container border fixed-tabs>
                <v-tab value='info'>资料</v-tab>
                <v-tab value='send'>投递</v-tab>
                <v-tab value='publish'>发布</v-tab>
                <v-tab value='manage'>管理</v-tab>
            </v-tabs>
        </div>
        <v-window data-aos='animate__fadeInUp' v-model='tab'>
            <v-window-item value='info'>
                <user-profile v-model='editing' />
            </v-window-item>
            <v-window-item value='send'>
                <user-send />
            </v-window-item>
            <v-window-item value='publish'>
                <user-publish />
            </v-window-item>
            <v-window-item value='manage'>
                <user-manage />
            </v-window-item>
        </v-window>
    </div>
</template>