const saveToken = (token) => {
    sessionStorage.setItem('token', token);
};

const getAccessToken = () => {
    const token = sessionStorage.getItem('token');
    const payload = extractPayload(token);
    if (!payload || isTokenExpired(payload)) {
        sessionStorage.removeItem('token');
        return null;
    }
    console.log(payload);
    return token;
};

const isExpiredInASeconds = (seconds) => {
    const token = sessionStorage.getItem('token');
    const payload = extractPayload(token);
    if (!payload) {
        return false;
    }
    const currentTimeInSeconds = new Date().getTime() / 1000;
    return payload["exp"] - currentTimeInSeconds < seconds;
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

const cleanUpToken = () => {
    sessionStorage.removeItem('token');
};

export {
    saveToken,
    getAccessToken,
    isExpiredInASeconds,
    cleanUpToken
}