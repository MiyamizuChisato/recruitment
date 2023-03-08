export default function imageCenter() {
    return {
        viewerEffect({ markdownBody }) {
            Array.from(markdownBody.querySelectorAll('img'))
                .forEach((img) => {
                    img.style.margin = '12px auto';
                    img.style.display = 'block';
                });
        }
    };
}