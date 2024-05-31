package br.unibh.sdm.backend_doasangue.entidades;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento{
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long idL;
        
        @NotBlank
        private String idDoador;
        
        @NotBlank 
        @Size(max = 100)
        private String unidadeSaude;        
        @NonNull
        @FutureOrPresent        
        private Date data;
        
        @NotBlank	
        private String status;

        public Agendamento() {
        }

        public Agendamento(Long idL, @NotBlank String idDoador, @NotBlank @Size(max = 100) String unidadeSaude,
                @NotBlank @Future Date data, @NotBlank String status) {
            this.idL = idL;
            this.idDoador = idDoador;
            this.unidadeSaude = unidadeSaude;
            this.data = data;
            this.status = status;
        }

        public Long getIdL() {
            return idL;
        }

        @Override
        public String toString() {
            return "Agendamento [idL=" + idL + ", idDoador=" + idDoador + ", unidadeSaude=" + unidadeSaude + ", data="
                    + data + ", status=" + status + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((idL == null) ? 0 : idL.hashCode());
            result = prime * result + ((idDoador == null) ? 0 : idDoador.hashCode());
            result = prime * result + ((unidadeSaude == null) ? 0 : unidadeSaude.hashCode());
            result = prime * result + ((data == null) ? 0 : data.hashCode());
            result = prime * result + ((status == null) ? 0 : status.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Agendamento other = (Agendamento) obj;
            if (idL == null) {
                if (other.idL != null)
                    return false;
            } else if (!idL.equals(other.idL))
                return false;
            if (idDoador == null) {
                if (other.idDoador != null)
                    return false;
            } else if (!idDoador.equals(other.idDoador))
                return false;
            if (unidadeSaude == null) {
                if (other.unidadeSaude != null)
                    return false;
            } else if (!unidadeSaude.equals(other.unidadeSaude))
                return false;
            if (data == null) {
                if (other.data != null)
                    return false;
            } else if (!data.equals(other.data))
                return false;
            if (status == null) {
                if (other.status != null)
                    return false;
            } else if (!status.equals(other.status))
                return false;
            return true;
        }

        public void setIdL(Long idL) {
            this.idL = idL;
        }

        public String getIdDoador() {
            return idDoador;
        }

        public void setIdDoador(String idDoador) {
            this.idDoador = idDoador;
        }

        public String getUnidadeSaude() {
            return unidadeSaude;
        }

        public void setUnidadeSaude(String unidadeSaude) {
            this.unidadeSaude = unidadeSaude;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        
}