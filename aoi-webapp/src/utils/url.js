export const getOAuthParams = (url) => {
    return url.substring(url.indexOf('?'));
};