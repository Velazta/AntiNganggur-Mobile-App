package com.l0123118.ravelin.antinganggur.data.repository

import com.l0123118.ravelin.antinganggur.data.local.database.JobDao
import com.l0123118.ravelin.antinganggur.data.local.database.JobEntity
import com.l0123118.ravelin.antinganggur.menulist.lowonganpage.Job
import com.l0123118.ravelin.antinganggur.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class JobRepository(private val jobDao: JobDao) {

    fun getAllJobs(): Flow<List<Job>> {
        return jobDao.getAllJobs().map { entities ->
            entities.map { it.toJob() }
        }
    }

    fun getTrendingJobs(): Flow<List<Job>> {
        return jobDao.getTrendingJobs().map { entities ->
            entities.map { it.toJob() }
        }
    }

    fun getRecentJobs(): Flow<List<Job>> {
        return jobDao.getRecentJobs().map { entities ->
            entities.map { it.toJob() }
        }
    }

    suspend fun getJobById(jobId: Int): Job? {
        return jobDao.getJobById(jobId)?.toJob()
    }

    fun searchJobs(query: String): Flow<List<Job>> {
        return jobDao.searchJobs(query).map { entities ->
            entities.map { it.toJob() }
        }
    }

    fun getJobsByLocation(location: String): Flow<List<Job>> {
        return jobDao.getJobsByLocation(location).map { entities ->
            entities.map { it.toJob() }
        }
    }

    suspend fun insertJob(job: Job) {
        jobDao.insertJob(job.toEntity())
    }

    suspend fun insertJobs(jobs: List<Job>) {
        jobDao.insertJobs(jobs.map { it.toEntity() })
    }

    suspend fun updateJob(job: Job) {
        jobDao.updateJob(job.toEntity())
    }

    suspend fun deleteJob(job: Job) {
        jobDao.deleteJob(job.toEntity())
    }

    suspend fun initializeDefaultJobs() {
        val existingJobs = jobDao.getAllJobs().first()
        if (existingJobs.isEmpty()) {
            val defaultJobs = getDefaultJobs()
            jobDao.insertJobs(defaultJobs)
        }
    }
    private fun getDefaultJobs(): List<JobEntity> {
        return listOf(
            JobEntity(
                title = "Frontend Developer",
                company = "AntiNganggur",
                location = "Kota Surakarta",
                type = "Full-Time",
                salary = "Rp22-32jt/Bulan",
                description = "Bangun antarmuka web yang menarik dan mudah digunakan.",
                iconResId = R.drawable.frontend,
                isTrending = true
            ),
            JobEntity(
                title = "Data Scientist",
                company = "AntiNganggur",
                location = "Yogyakarta",
                type = "Full-Time",
                salary = "Rp18-30jt/Bulan",
                description = "Mencari Data Scientist yang mahir dalam analisis data, machine learning, dan membangun model prediktif untuk memecahkan masalah bisnis.",
                iconResId = R.drawable.datascientist,
                isTrending = true
            ),
            JobEntity(
                title = "Mobile Developer",
                company = "AntiNganggur",
                location = "Surabaya",
                type = "Full-Time",
                salary = "Rp20-30jt/Bulan",
                description = "Mengembangkan dan memelihara aplikasi mobile (Android/iOS).",
                iconResId = R.drawable.mobiledev,
                isTrending = true
            ),
            JobEntity(
                title = "Cyber Security Analyst",
                company = "AntiNganggur",
                location = "Bandung",
                type = "Full-Time",
                salary = "Rp20-30jt/Bulan",
                description = "Melindungi sistem komputer dan jaringan dari ancaman siber.",
                iconResId = R.drawable.cyberanalyst,
                isTrending = true
            ),

            // Recent Jobs
            JobEntity(
                title = "Backend Developer",
                company = "AntiNganggur",
                location = "Bandung",
                type = "Full-Time",
                salary = "Rp20–30jt/Bulan",
                description = "Bangun dan kembangkan API serta sistem backend perusahaan. Tanggung jawab meliputi desain database, implementasi logika bisnis, dan integrasi layanan.",
                iconResId = R.drawable.backend,
                isTrending = false
            ),
            JobEntity(
                title = "DevOps Engineer",
                company = "AntiNganggur",
                location = "Jakarta",
                type = "Hybrid",
                salary = "Rp25–35jt/Bulan",
                description = "Kelola infrastruktur dan deployment pipeline dengan aman dan efisien. Fokus pada otomatisasi, pemantauan, dan peningkatan skalabilitas sistem.",
                iconResId = R.drawable.devops,
                isTrending = false
            ),
            JobEntity(
                title = "Cloud Architect",
                company = "AntiNganggur",
                location = "Jakarta",
                type = "Full-Time",
                salary = "Rp30–45jt/Bulan",
                description = "Merancang dan mengimplementasikan solusi cloud scalable di platform seperti AWS, Azure, atau GCP. Memastikan keamanan dan performa infrastruktur cloud.",
                iconResId = R.drawable.cloudarchitect,
                isTrending = false
            ),
            JobEntity(
                title = "Software Engineer",
                company = "AntiNganggur",
                location = "Yogyakarta",
                type = "Full-Time",
                salary = "Rp15-28jt/Bulan",
                description = "Merancang, mengembangkan, dan memelihara aplikasi perangkat lunak yang scalable dan efisien menggunakan bahasa pemrograman modern. Berkontribusi dalam seluruh siklus hidup pengembangan perangkat lunak.",
                iconResId = R.drawable.softwareengineer,
                isTrending = false
            ),
            JobEntity(
                title = "Network Administrator",
                company = "AntiNganggur",
                location = "Surabaya",
                type = "Full-Time",
                salary = "Rp10-18jt/Bulan",
                description = "Mengelola, mengkonfigurasi, dan memelihara infrastruktur jaringan perusahaan. Memastikan ketersediaan jaringan yang tinggi dan keamanan data.",
                iconResId = R.drawable.netadministrator,
                isTrending = false
            ),
            JobEntity(
                title = "Frontend Developer WFH",
                company = "AntiNganggur",
                location = "Jakarta",
                type = "Full-Time • Remote",
                salary = "Rp18-28jt/Bulan",
                description = "Mengembangkan antarmuka web responsif menggunakan React.js dan Vue.js. Bekerja secara remote dengan tim yang tersebar di seluruh Indonesia. Membutuhkan pengalaman minimal 2 tahun dalam frontend development.",
                iconResId = R.drawable.frontend,
                isTrending = false
            ),
            JobEntity(
                title = "Backend Developer WFH",
                company = "AntiNganggur",
                location = "Surabaya",
                type = "Full-Time • Remote",
                salary = "Rp20-32jt/Bulan",
                description = "Membangun dan memelihara API serta sistem backend menggunakan Node.js, Python, atau Java. Posisi full remote dengan fleksibilitas waktu kerja. Pengalaman dengan database dan cloud services diperlukan.",
                iconResId = R.drawable.backend,
                isTrending = false
            )
        )
    }
}

fun JobEntity.toJob(): Job {
    return Job(
        title = this.title,
        company = this.company,
        location = this.location,
        type = this.type,
        salary = this.salary,
        description = this.description,
        iconResId = this.iconResId,
        isTrending = this.isTrending
    )
}

fun Job.toEntity(): JobEntity {
    return JobEntity(
        title = this.title,
        company = this.company,
        location = this.location,
        type = this.type,
        salary = this.salary,
        description = this.description,
        iconResId = this.iconResId,
        isTrending = this.isTrending
    )
}
