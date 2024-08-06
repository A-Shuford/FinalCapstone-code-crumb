document.addEventListener('DOMContentLoaded', () => {
    const hamburgerMenu = document.querySelector('.hamburger-menu');
    const navbar = document.getElementById('navbar');

    hamburgerMenu.addEventListener('click', () => {
        navbar.style.display = navbar.style.display === 'block' ? 'none' : 'block';
    });
});
