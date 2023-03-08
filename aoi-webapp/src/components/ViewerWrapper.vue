<script setup>
import SupViewer from './SupViewer.vue';
import SubViewer from './SubViewer.vue';
</script>
<template>
    <div p='3' rounded='md:1 2' bg-container>
        <div p='2'>
            <sup-viewer />
        </div>
        <template v-if='false'>
            <div p='2' m='b-3' rounded='sm' v-for='i in 4' bg-fill>
                <sub-viewer />
            </div>
        </template>
        <div text-secondary flex-end>
            <i m='x-1' i-material-symbols-horizontal-rule-rounded />
            <v-btn size='small' color='transparent' flat>
                <span>展开114条回复</span>
            </v-btn>
        </div>
    </div>
</template>