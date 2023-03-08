<script setup>
import { useUserStore } from '../../store/userStore.js';

defineProps({
    content: String
});
const userStore = useUserStore();
</script>
<template>
    <div m='l-42px y-4' flex-end>
        <div m='x-2' p='2' rounded='2' border shadow-box bg-container text-regular>
            {{ content}}
        </div>
        <v-avatar size='42' :image='userStore.userProfile.avatar' />
    </div>
</template>