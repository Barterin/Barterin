<?php

function ApiPost($path)
{
    $curl = curl_init();

    $accessToken = get_cookie("accessToken");
    $apiUrl = getenv('app.apiUrl');

    curl_setopt_array($curl, array(
        CURLOPT_URL => "$apiUrl/$path",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'POST',
        CURLOPT_HTTPHEADER => array(
            "Authorization: Bearer $accessToken"
        ),
    ));

    $response = curl_exec($curl);
    curl_close($curl);
    return $response;
}

function ApiGet($path)
{
    $curl = curl_init();

    $accessToken = get_cookie("accessToken");
    $apiUrl = getenv('app.apiUrl');

    curl_setopt_array($curl, array(
        CURLOPT_URL => "$apiUrl/$path",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
        CURLOPT_HTTPHEADER => array(
            "Authorization: Bearer $accessToken"
        ),
    ));

    $response = curl_exec($curl);

    curl_close($curl);
    return $response;
}

function ApiGetNoAuth($path)
{
    $curl = curl_init();

    $apiUrl = getenv('app.apiUrl');

    curl_setopt_array($curl, array(
        CURLOPT_URL => "$apiUrl/$path",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
    ));

    $response = curl_exec($curl);

    curl_close($curl);
    return $response;
}