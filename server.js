const express = require('express');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(express.json());
app.use(express.static(__dirname));

app.post('/webhook', async (req, res) => {
    try {
        const response = await fetch('https://moonjunwon.app.n8n.cloud/webhook-test/77753ce3-6250-4dbc-b760-b58b2d5c3ffb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(req.body)
        });
        
        const data = await response.text();
        res.json({ message: data });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({ error: error.message });
    }
});

app.listen(PORT, () => {
    console.log(`서버가 http://localhost:${PORT} 에서 실행중입니다`);
    console.log(`채팅 페이지: http://localhost:${PORT}/chat.html`);
});