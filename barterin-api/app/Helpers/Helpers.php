<?php

use \Illuminate\Support\Str;
use Firebase\JWT\JWT;

function GetStatusCode(\Exception $ex)
{
    return $ex->getCode() > 511 || $ex->getCode() <= 0 ? 500 : $ex->getCode();
}
