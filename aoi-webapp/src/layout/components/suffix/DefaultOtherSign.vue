<script setup>
import { getPath } from '../../../api/oauth.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { ref } from 'vue';
import { debounce } from 'lodash';

const tip = ref(false);
const openOAuthTrigger = debounce(async (type) => {
    const { code, data } = await getPath(type);
    onApiSuccess(code, () => {
        window.open(data);
    });
}, 1000);
</script>
<template>
    <v-snackbar timeout='3000' v-model='tip'>
        正在请求授权地址
        <template v-slot:actions>
            <v-btn color='pink' variant='text' @click='tip = false'>
                关 闭
            </v-btn>
        </template>
    </v-snackbar>
    <div p='x-12 y-4' flex-center>
        <div class='line' flex='1'></div>
        <div p='x-4' class='other-label' text-strong>
            其他登陆方式
        </div>
        <div class='line' flex='1'></div>
    </div>
    <div flex-center>
        <v-btn @click='openOAuthTrigger(`github`),tip = true' icon variant='text'>
            <svg class='icon' aria-hidden='true'>
                <use xlink:href='#icon-github'></use>
            </svg>
        </v-btn>
        <v-btn m='x-2' icon variant='text'>
            <svg class='icon' aria-hidden='true'>
                <use xlink:href='#icon-QQ'></use>
            </svg>
        </v-btn>
        <v-btn @click='openOAuthTrigger(`google`)' icon variant='text'>
            <svg class='icon' aria-hidden='true'>
                <use xlink:href='#icon-bilibili'></use>
            </svg>
        </v-btn>
    </div>
</template>
<style scoped>
.line {
    border-bottom: 1px solid var(--c-border);
}
</style>