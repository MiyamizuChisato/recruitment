<script setup>
defineProps({
    color: String
});

</script>
<template>
    <div data-aos='animate__fadeInDown' data-aos-once='true' p='y-5' flex-between-x>
        <div flex>
            <div w='1' rounded='full' :class='color'></div>
            <div m='x-2' text-strong>
                <slot name='default' />
            </div>
        </div>
        <div>
            <slot name='suffix' />
        </div>
    </div>
</template>
<style scoped>
.primary {
    background-color: var(--c-primary);
}

.success {
    background-color: var(--c-success);
}

.warning {
    background-color: var(--c-warning);
}

.danger {
    background-color: var(--c-danger);
}

.info {
    background-color: var(--c-info);
}
</style>

