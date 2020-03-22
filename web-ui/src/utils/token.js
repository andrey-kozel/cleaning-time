const saveToken = (token) => {
    sessionStorage.setItem('token', token);
};

const getAccessToken = () => {
    const token = sessionStorage.getItem('token');
    const payload = extractPayload(token);
    if (!payload || isTokenExpired(payload)) {
        return null;
    }
    console.log(payload);
    return `Bearer ${token}`;
};

const extractPayload = (token) => {
    if (!token) {
        return null;
    }

    const tokenParts = token.split(".");
    if (tokenParts.length !== 3) {
        return null;
    }
    const stringPayload = atob(tokenParts[1]);
    return JSON.parse(stringPayload);
};

const isTokenExpired = (payload) => {
    if (!payload) {
        return true;
    }
    const currentTimeInSeconds = new Date().getTime() / 1000;
    return payload["exp"] < currentTimeInSeconds;

};

export {
    saveToken,
    getAccessToken
}