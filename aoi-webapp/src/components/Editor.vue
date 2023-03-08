<script setup>
import { onBeforeMount, ref } from 'vue';

defineProps({
    modelValue: String,
    send: Function
});
const emit = defineEmits();
const textareaRef = ref(null);
const replyFlag = ref(false);
const koto = ref('');
const name = ref('');
const toUserId = ref(0);
const parentId = ref(0);
const replyHandler = (nickname, uid, pid) => {
    name.value = nickname;
    toUserId.value = uid;
    parentId.value = pid;
    replyFlag.value = true;
    textareaRef.value.focus();
};
const replyCancel = () => {
    replyFlag.value = false;
    toUserId.value = 0;
    parentId.value = 0;
};
const getReplyInfo = () => {
    return {
        toUserId: toUserId.value,
        parentId: parentId.value
    };
};
const autoHeight = (e) => {
    emit('update:modelValue', e.target.value);
    if (e.target.scrollHeight <= 320) {
        textareaRef.value.style.height = '180px';
        textareaRef.value.style.height = e.target.scrollHeight + 'px';
    }
};
onBeforeMount(async () => {
    const response = await fetch('https://v1.hitokoto.cn/?encode=text');
    koto.value = await response.text();
});
defineExpose({
    replyHandler,
    getReplyInfo
});
</script>
<template>
    <div rounded='md:t-1 t-2' p='1' border='1px b-solid border-light dark:border-dark' bg-fill flex-between-x>
        <div flex='1' h='100%' m='x-2' text='13px' flex-between-x>
            <span v-if='replyFlag' text-success>@{{ name }}</span>
            <span v-else text-secondary>{{ koto }}</span>
        </div>
        <v-btn v-if='replyFlag' @click='replyCancel' color='transparent' size='small' flat>
            <span text-danger>取消回复</span>
        </v-btn>
        <v-btn @click='send' color='transparent' size='small' flat>
            <span text-primary>发送</span>
        </v-btn>
    </div>
    <textarea @input='autoHeight' :value='modelValue' ref='textareaRef' p='3' w='100%' outline='none'
              rounded='md:b-1 b-2' placeholder='评论留言在这里...' text-regular bg-container />
</template>
<style scoped>
textarea {
    resize: none;
    height: 180px;
}

textarea::-webkit-scrollbar {
    background-color: var(--c-container);
}
</style>