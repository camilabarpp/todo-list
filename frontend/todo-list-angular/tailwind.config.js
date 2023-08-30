/** @type {import('tailwindcss').Config} */
module.exports = {
  important: true,
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
      },
      screens: {

      }
    }
  },
  plugins: [
    require('@tailwindcss/line-clamp'),
  ],
}
