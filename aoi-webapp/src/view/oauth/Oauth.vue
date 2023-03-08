<script setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { getOAuthParams } from '../../utils/url.js';
import { postCode } from '../../api/oauth.js';

const route = useRoute();
const type = route.params.type;
const error = ref(false);
onMounted(async () => {
    const { code, data } = await postCode(type, getOAuthParams(location.href));
    if (code < 0) {
        error.value = true;
    } else {
        localStorage.setItem('token', data);
        window.close();
    }
});
const close = () => {
    window.close();
};
</script>
<template>
    <div h='100vh' p='10' flex-center bg-screen>
        <div p='10' rounded='lg' shadow='md' v-if='error' text='18px' font='500' text-strong bg-container border>
            <p>频繁刺激 {{ type }} 酱的敏感点，ヾ(｡ꏿ﹏ꏿ)ﾉ </p>
            <br>
            <p>导致 {{ type }} 酱被玩坏捏 ヾ(｡ꏿ﹏ꏿ)ﾉ </p>
            <br>
            <p>说人话就是认证太频繁或者万能的网络出问题啦...</p>
            <br>
            <div flex-end>
                <v-btn @click='close' variant='text' bg-hover><span text-danger>关闭</span></v-btn>
            </div>
        </div>
        <div v-else>
            <v-progress-circular
                indeterminate
                color='blue'
            ></v-progress-circular>
        </div>
    </div>
</template>