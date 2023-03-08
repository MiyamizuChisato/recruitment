<script setup>

</script>
<template>
    <footer text-center text-secondary flex-center flex-col>
        <p class='sakura'>
            <i i-twemoji-cherry-blossom />
        </p>
        <p>
            成都师范学院毕业设计
        </p>
        <p>
            &copy;190015511169
        </p>
    </footer>
</template>
<style scoped>
footer {
    margin-top: 60px;
    padding: 20px 120px;
}

.sakura {
    opacity: 0.4;
    animation: rotate 10s linear 0ms infinite;
}

p {
    margin-top: 4px;
}

@keyframes rotate {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
}
</style>
