export default function modifyHrefTarget() {
    return {
        viewerEffect({ markdownBody }) {
            Array.from(markdownBody.querySelectorAll('a'))
                .filter((a) => {
                    return Boolean(a.getAttribute('href'));
                })
                .forEach((a) => {
                    a.setAttribute('target', '_blank');
                });
        }
    };
}