export const onApiSuccess = (code = -1, fn) => {
    if (code >= 0) {
        fn();
    }
};