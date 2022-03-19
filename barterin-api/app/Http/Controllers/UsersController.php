<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use App\Models\User;

class UsersController extends Controller
{

    public function test(Request $request)
    {
        return response()->json(
            User::all()
        );
    }
}
