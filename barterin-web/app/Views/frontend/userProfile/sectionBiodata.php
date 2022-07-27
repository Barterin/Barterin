<div class="col">
    <div class="row">
        <div class="col-1" id="profile-picture-preview">

        </div>
        <div class="mt-3">
            <label for="formFile" class="form-label fw-bold">Upload Foto Profile</label>
            <input type="file" class="form-control" id="formFile">
        </div>
    </div>
    <form id="userProfileForm" enctype="multipart/form-data">
        <!-- Nama -->
        <label for="Nama" class="form-label fw-bold mt-3">
            Nama
        </label>
        <input type="text" placeholder="Nama Lengkap" class="form-control" id="fullname" name="fullname"
            aria-describedby="nama" autofocus />
        <!-- Tanggal Lahir -->
        <label for="Tanggal Lahir" class="form-label fw-bold mt-3">
            Tanggal Lahir</label>
        <input type="date" placeholder="Tanggal Lahir" class="form-control" id="tanggalLahir" name="born"
            aria-describedby="tanggalLahir" />
        <!-- Jenis Kelamin -->
        <label for="Jenis Kelamin" class="form-label fw-bold mt-3">
            Jenis Kelamin</label>
        <select class="text-start form-select" name="gender" id="jkDropdown" data-bs-toggle="dropdown"
            aria-expanded="false">
            Pilih Jenis Kelamin
            <option value="male">Laki - Laki</option>
            <option value="female">Perempuan</option>
        </select>
        <label for="Number" class="form-label fw-bold mt-3">
            Nomor HP
        </label>
        <input type="number" class="form-control" id="Number" name="phone" aria-describedby="Number" />
        <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary mt-3 align-items-end">
                Simpan
            </button>
        </div>
    </form>
</div>