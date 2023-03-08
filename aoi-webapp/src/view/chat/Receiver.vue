<script setup>
defineProps({
    avatar: String,
    content: String
});
</script>
<template>
    <div m='r-42px y-4' flex>
        <v-avatar size='42' :image='avatar' />
        <div m='x-2' p='2' rounded='2' border shadow-box bg-container text-regular>
            {{ content }}
        </div>
    </div>
</template>
