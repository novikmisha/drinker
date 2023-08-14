module.exports = {
    mode: process.env.NODE_ENV ? 'jit' : undefined,
    purge: ["./src/**/*.html", "./src/**/*.js"],
    darkMode: false,
    plugins: [require("daisyui")],
    daisyui: {
        themes: ["retro"],
    }
}
