import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile/profile.service';
import { Perfil } from '../../interfaces/profile.interface';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})

export class ProfileComponent implements OnInit {
  perfil: Perfil | null = null;

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.profileService.getUsuarioPerfil().subscribe({
      next: (perfil: Perfil) => {
        this.perfil = perfil;
      },
      error: (err) => {
        console.error('Error fetching profile:', err);
      }
    });
  }
}
