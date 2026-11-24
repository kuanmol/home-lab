
const API_URL = "/api/anime";

let animeCollection = [];

async function loadAnime() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load anime");
        }

        animeCollection = await response.json();

        updateStats();
        renderAnime();

    } catch (error) {
        document.getElementById("animeList").innerHTML =
            '<p class="empty-message">Unable to connect to server.</p>';

        console.error(error);
    }
}

function updateStats() {
    document.getElementById("totalCount").textContent =
        animeCollection.length;

    document.getElementById("watchingCount").textContent =
        animeCollection.filter(a => a.status === "WATCHING").length;

    document.getElementById("completedCount").textContent =
        animeCollection.filter(a => a.status === "COMPLETED").length;

    document.getElementById("plannedCount").textContent =
        animeCollection.filter(a => a.status === "PLAN_TO_WATCH").length;
}

function renderAnime() {
    const list = document.getElementById("animeList");

    const search = document.getElementById("searchInput")
        .value.toLowerCase();

    const filter = document.getElementById("statusFilter").value;

    const filteredAnime = animeCollection.filter(anime => {
        const matchesSearch = anime.title.toLowerCase().includes(search);
        const matchesStatus = filter === "ALL" || anime.status === filter;

        return matchesSearch && matchesStatus;
    });

    if (filteredAnime.length === 0) {
        list.innerHTML = '<p class="empty-message">No anime found. Add your first anime! 🍥</p>';
        return;
    }

    list.innerHTML = filteredAnime.map(anime => `
        <div class="anime-card">
            <h3>${escapeHTML(anime.title)}</h3>
            <p>Genre: ${escapeHTML(anime.genre)}</p>
            <p>Episodes: ${anime.episodes}</p>
            <p>Rating: ⭐ ${anime.rating}/10</p>
            <span class="status">${formatStatus(anime.status)}</span>

            <div class="card-actions">
                <button onclick="editAnime(${anime.id})">Edit</button>
                <button class="delete-btn"
                    onclick="deleteAnime(${anime.id})">Delete</button>
            </div>
        </div>
    `).join("");
}

function escapeHTML(value) {
    return String(value ?? "").replace(/[&<>"']/g, char => ({
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#39;"
    })[char]);
}

function formatStatus(status) {
    return {
        WATCHING: "Watching",
        COMPLETED: "Completed",
        PLAN_TO_WATCH: "Plan to Watch"
    }[status] || status;
}

function openModal() {
    document.getElementById("animeForm").reset();
    document.getElementById("animeId").value = "";
    document.getElementById("modalTitle").textContent = "Add New Anime";

    document.getElementById("animeModal").classList.add("show");
}

function closeModal() {
    document.getElementById("animeModal").classList.remove("show");
}

async function editAnime(id) {
    const anime = animeCollection.find(a => a.id === id);

    if (!anime) return;

    document.getElementById("animeId").value = anime.id;
    document.getElementById("title").value = anime.title;
    document.getElementById("genre").value = anime.genre;
    document.getElementById("episodes").value = anime.episodes;
    document.getElementById("rating").value = anime.rating;
    document.getElementById("status").value = anime.status;

    document.getElementById("modalTitle").textContent = "Edit Anime";
    document.getElementById("animeModal").classList.add("show");
}

document.getElementById("animeForm").addEventListener("submit", async event => {
    event.preventDefault();

    const id = document.getElementById("animeId").value;

    const anime = {
        title: document.getElementById("title").value.trim(),
        genre: document.getElementById("genre").value.trim(),
        episodes: Number(document.getElementById("episodes").value),
        rating: Number(document.getElementById("rating").value),
        status: document.getElementById("status").value
    };

    if (!anime.title || !anime.genre ||
        anime.episodes < 0 || anime.rating < 0 || anime.rating > 10) {
        alert("Please enter valid anime details.");
        return;
    }

    try {
        const response = await fetch(
            id ? `${API_URL}/${id}` : API_URL,
            {
                method: id ? "PUT" : "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(anime)
            }
        );

        if (!response.ok) {
            throw new Error("Unable to save anime");
        }

        closeModal();
        await loadAnime();

    } catch (error) {
        alert(error.message);
    }
});

async function deleteAnime(id) {
    if (!confirm("Are you sure you want to delete this anime?")) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Unable to delete anime");
        }

        await loadAnime();

    } catch (error) {
        alert(error.message);
    }
}

document.getElementById("searchInput")
    .addEventListener("input", renderAnime);

document.getElementById("statusFilter")
    .addEventListener("change", renderAnime);

loadAnime();
